import { h } from 'preact';
import { Link } from 'preact-router/match';
import TopAppBar from 'preact-material-components/TopAppBar';
import 'preact-material-components/TopAppBar/style.css';
import style from './style.css';

const Header = () => (
    <TopAppBar onNav={() => {return;}}>
        <TopAppBar.Row class={style.header}>
            <TopAppBar.Section align-start>
                <TopAppBar.Title class={style.title}>F1 Schedule App</TopAppBar.Title>
            </TopAppBar.Section>
            <TopAppBar.Section align-end>
                <nav>
                    <Link activeClassName={style.active} href="/">Home</Link>
                    <Link activeClassName={style.active} href="/features">Features</Link>
                    <Link activeClassName={style.active} href="/information">More</Link>
                </nav>
            </TopAppBar.Section>
        </TopAppBar.Row>
    </TopAppBar>
);

export default Header;
