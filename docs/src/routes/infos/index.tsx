import { h } from 'preact';
import Button from 'preact-material-components/Button';
import 'preact-material-components/Button/style.css';
import CardComponent  from '../../components/card';
import Icon from 'preact-material-components/Icon';
import PageContainer from '../../components/page';
import style from './style.css';

const Information = () => {
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
				<CardComponent styling={{ display: "flex", alignItems: "center", flexDirection: "column", position: "relative", zIndex: "1" }}>
					<h2>Source code</h2>
					<Button outlined raised ripple class={style.button} href="https://github.com/tim0-12432/f1-schedule-app" target="_blank">
						<Icon>integration_instructions</Icon>
						Github
					</Button>
				</CardComponent>
			</section>
		</PageContainer>
	);
};

export default Information;
