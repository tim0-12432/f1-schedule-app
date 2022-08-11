import { h } from 'preact';
import { Route, Router } from 'preact-router';
import baseroute from './baseroute';

import Header from './header';

import Home from '../routes/home';
import Features from '../routes/features';
import Information from '../routes/infos';

import Footer from './footer';

const App = () => {
    return (
        <div id="app">
            <Header />
            <Router>
                <Route path={`${baseroute}/`} default component={Home} />
                <Route path={`${baseroute}/features`} component={Features} />
                <Route path={`${baseroute}/information`} component={Information} />
            </Router>
            <Footer />
        </div>
    );
};

export default App;
