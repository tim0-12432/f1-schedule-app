import { h } from 'preact';
import { Route, Router } from 'preact-router';

import Header from './header';

import Home from '../routes/home';
import Features from '../routes/features';
import Information from '../routes/infos';

import Footer from './footer';

const App = () => (
	<div id="app">
        <Header />
        <Router>
            <Route path="/" default component={Home} />
            <Route path="/features" component={Features} />
            <Route path="/information" component={Information} />
        </Router>
        <Footer />
    </div>
);

export default App;
