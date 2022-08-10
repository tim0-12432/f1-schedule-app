import { h } from 'preact';
import Card from 'preact-material-components/Card';
import CardComponent from '../../components/card';
import Emoji from '../../components/emoji';
import PageContainer from '../../components/page';
import circuit from '../../assets/images/circuit.gif';
import circuits from '../../assets/images/circuits.gif';
import race from '../../assets/images/race_results.gif';
import schedule from '../../assets/images/races.gif';
import driver from '../../assets/images/driver.jpg';
import driverRanking from '../../assets/images/driver_ranking.gif';
import team from '../../assets/images/team.jpg';
import teamRanking from '../../assets/images/team_ranking.gif';
import style from './style.css';

const Features = () => {
    return (
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
				<Card class={style.imgRight}>
					<img src={circuit} alt="circuit" />
				</Card>
				<div class={style.spacer1} />
				<CardComponent styling={{ position: "relative", paddingLeft: "4rem", paddingRight: "calc(10rem * 640/300)", zIndex: "1" }}>
					<Card class={style.imgSmallLeft}>
						<img src={circuits} alt="circuits" />
					</Card>
					<h2>List of circuits</h2>
					<p>All circuits used in Formula 1 are listed in a easy-to-use listview, where single circuits can be selected.</p>
					<p>When tapping on a circuit, you can see detailed information about that circuit.</p>
					<p>Some of the details are...</p>
					<ul>
						<li>the circuit's name...</li>
						<li>the country...</li>
						<li>...and a map showing the circuit!</li>
					</ul>
					<p>For even more information you can use the Wikipedia button and have a quick look on the circuit's Wikipedia page.</p>
				</CardComponent>
				<div class={style.spacer2} />
			</section>
			<section class={style.section2}>
				<Card class={style.imgLeft}>
					<img src={race} alt="race result" />
				</Card>
				<div class={style.spacer2} />
				<CardComponent styling={{ position: "relative", paddingRight: "4rem", paddingLeft: "calc(10rem * 640/300)", zIndex: "1" }}>
					<Card class={style.imgSmallRight}>
						<img src={schedule} alt="schedule" />
					</Card>
					<h2>Race schedule</h2>
					<p>Here you can see listed all recent and upcoming races (Grand prix) for the current season.</p>
					<p>By clicking on a specific race, you open a screen with details about the race.</p>
					<p>If the race is not over yet, you see a counter of days until the race starts. Otherwise you see all results the drivers had achieved.</p>
					<p>Information about the starttime and place is always displayed.</p>
					<p>There are also some different markers for the race result status:</p>
					<ul>
						<li><Emoji emojicode="finished" />: Finished</li>
						<li><Emoji emojicode="finished_white" />: Finished, but with laps difference</li>
						<li><Emoji emojicode="accident" />: Accident</li>
						<li><Emoji emojicode="damage" />: Damage without accident</li>
						<li><Emoji emojicode="retired" />: Retired</li>
					</ul>
				</CardComponent>
				<div class={style.spacer1} />
			</section>
			<section class={style.section3}>
				<Card class={style.imgRight}>
					<img src={driverRanking} alt="driver ranking" />
				</Card>
				<div class={style.spacer1} />
				<CardComponent styling={{ position: "relative", paddingLeft: "4rem", paddingRight: "calc(10rem * 640/300)", zIndex: "1" }}>
					<Card class={style.imgSmallLeft}>
						<img src={driver} alt="driver" />
					</Card>
					<h2>Championship ranking of drivers</h2>
					<p>In this screen all driver are ranked after their current points in championship.</p>
					<p>Next to other information, you can see...</p>
					<ul>
						<li>the driver's name...</li>
						<li>nationality...</li>
						<li>and team...</li>
					</ul>
					<p>...on the ranking itself or in the details screen for the specific driver.</p>
					<p>Of course you can look up even more information on Wikipedia by using the button on the bottom.</p>
				</CardComponent>
				<div class={style.spacer2} />
			</section>
			<section class={style.section4}>
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
				<Card class={style.imgLeft}>
					<img src={teamRanking} alt="team ranking" />
				</Card>
				<div class={style.spacer2} />
				<CardComponent styling={{ position: "relative", paddingRight: "4rem", paddingLeft: "calc(10rem * 640/300)", zIndex: "1" }}>
					<Card class={style.imgSmallRight}>
						<img src={team} alt="team" />
					</Card>
					<h2>Team ranking</h2>
					<p>What you can see on this screen is the ranking of teams for the current season.</p>
					<p>By tapping on a team you open another screen for detailed information about the constructor.</p>
					<p>For example engine constructor, nationality, current drivers and their points, sum of points, etc.</p>
				</CardComponent>
				<div class={style.spacer1} />
			</section>
		</PageContainer>
	);
};

export default Features;
