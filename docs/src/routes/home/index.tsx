import { h } from 'preact';
import PageContainer from '../../components/page';
import CardComponent  from '../../components/card';
import Card from 'preact-material-components/Card';
import img from '../../assets/images/driver.jpg';
import 'preact-material-components/Card/style.css';
import style from './style.css';

const Home = () => (
	<PageContainer>
		<section class={style.section1}>
		    <div class={style.divider}>
		        <svg data-name="Layer 1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1200 120" preserveAspectRatio="none">
                    <path d="M321.39,56.44c58-10.79,114.16-30.13,172-41.86,82.39-16.72,168.19-17.73,250.45-.39C823.78,31,906.67,72,985.66,92.83c70.05,18.48,146.53,26.09,214.34,3V0H0V27.35A600.21,600.21,0,0,0,321.39,56.44Z" class={style.shapeFill}></path>
                </svg>
                <svg data-name="Layer 1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1200 120" preserveAspectRatio="none">
                    <rect width="1200" height="120" class={style.shapeFill}></rect>
                </svg>
                <svg data-name="Layer 1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1200 120" preserveAspectRatio="none">
                    <path d="M321.39,56.44c58-10.79,114.16-30.13,172-41.86,82.39-16.72,168.19-17.73,250.45-.39C823.78,31,906.67,72,985.66,92.83c70.05,18.48,146.53,26.09,214.34,3V0H0V27.35A600.21,600.21,0,0,0,321.39,56.44Z" class={style.shapeFill}></path>
                </svg>
            </div>
		    <Card class={style.img}>
		        <img src={img} alt="driver" />
		    </Card>
		    <div class={style.spacer} />
		    <CardComponent styling={{ paddingLeft: "calc(10rem * 1600/770)", zIndex: "1" }}>
		        <p>I wanted to improve my Java app-development skills and create a beautiful designed app to quickly access information about the next race.</p>
                <p>So I developed this android app, which displays all important information about Formula 1 drivers, teams, races and circuits.</p>
                <p>I hope you will love it.</p>
		    </CardComponent>
		</section>
		<section class={style.section2}>
		    <CardComponent>
                <h2>Available in following languages</h2>
                <ul>
                    <li><em-emoji shortcodes=":gb:" set="google"> </em-emoji><span>English</span></li>
                    <li><em-emoji shortcodes=":de:" set="google"> </em-emoji><span>German</span></li>
                </ul>
            </CardComponent>
		</section>
	</PageContainer>
);

export default Home;
