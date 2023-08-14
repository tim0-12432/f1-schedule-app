package de.tim0_12432.f1_schedule_app.data.cache;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.tim0_12432.f1_schedule_app.TestLog;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.tim0_12432.f1_schedule_app.data.Resource;
import de.tim0_12432.f1_schedule_app.data.entity.Circuit;
import de.tim0_12432.f1_schedule_app.data.entity.Race;
import de.tim0_12432.f1_schedule_app.data.entity.builder.LocationBuilder;

@RunWith(RobolectricTestRunner.class)
@Config(shadows={TestLog.class}, manifest=Config.NONE)
public class CachingServiceTest {
    private CachingService cachingService;

    private StorageService storageMock;

    private List<Race> getTestRaces() {
        List<Race> races = new ArrayList<>();
        races.add(new Race(1993, 1, "Test 1", new Circuit(new LocationBuilder().build(), "Circ 1", ""), "", new Date(0), new Time(0)));
        races.add(new Race(1993, 2, "Test 2", new Circuit(new LocationBuilder().build(), "Circ 2", ""), "", new Date(8367623), new Time(8367689)));
        return races;
    }

    private HashMap<String, Object> getTestCache(long time) {
        HashMap<String, Object> cache = new HashMap<>();
        cache.put("time", time);
        cache.put("data", getTestRaces());
        return cache;
    }

    @Before
    public void setUp() {
        cachingService = new CachingService(null);
        storageMock = mock(StorageService.class);
        cachingService.storageService = storageMock;
    }

    @Test
    public void testWriteData() throws IOException {
        Resource res = Resource.SCHEDULE;
        String url = "url";
        List<Race> races = getTestRaces();
        Map<String, Object> cache = new HashMap<>();
        cache.put("time", System.currentTimeMillis());
        cache.put("data", races);
        cachingService.writeData(res, url, races);
        verify(storageMock, times(1)).writeObject(eq(CachingService.getKey(res, url)), eq(cache));
    }

    @Test
    public void testReadData() throws IOException, ClassNotFoundException {
        List<Race> races = getTestRaces();

        String key = CachingService.getKey(Resource.SCHEDULE, "url");
        when(storageMock.readObject(key)).thenReturn("Abcde");

        Object result = cachingService.readData(Resource.SCHEDULE, "url");
        Assert.assertNull(result);

        when(storageMock.readObject(key)).thenReturn(getTestCache(System.currentTimeMillis()));

        result = cachingService.readData(Resource.SCHEDULE, "url");
        Assert.assertNotNull(result);
        Assert.assertEquals(2, ((List<Race>) result).size());
        Assert.assertEquals(races.get(0).getName(),((List<Race>) result).get(0).getName());
    }

    @Test
    public void testShouldUpdateCache() throws IOException, ClassNotFoundException {
        String key = CachingService.getKey(Resource.SCHEDULE, "url");
        when(storageMock.readObject(key)).thenReturn(getTestCache(System.currentTimeMillis()));

        Assert.assertTrue(cachingService.shouldUpdateCache(new Date(System.currentTimeMillis()), Resource.SCHEDULE, "url"));

        Assert.assertFalse(cachingService.shouldUpdateCache(null, Resource.SCHEDULE, "url"));

        when(storageMock.readObject(key)).thenReturn(getTestCache(System.currentTimeMillis() - 1000L * 60 * 60 * 24 * 5));
        Assert.assertTrue(cachingService.shouldUpdateCache(null, Resource.SCHEDULE, "url"));

        when(storageMock.readObject(key)).thenReturn("Abcdef");
        Assert.assertTrue(cachingService.shouldUpdateCache(null, Resource.SCHEDULE, "url"));
    }

    @Test
    public void testClearCache() {
        Resource res = Resource.SCHEDULE;
        String url = "url";
        cachingService.clearCache(res, url);
        verify(storageMock, times(1)).deleteObject(CachingService.getKey(res, url));
    }

    @After
    public void tearDown() {
        cachingService = null;
        storageMock = null;
    }
}
