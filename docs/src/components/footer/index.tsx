import { h } from 'preact';
import CardComponent from '../card';
import style from './style.css';

const Footer = () => (
    <footer class={style.footer}>
        <CardComponent>
            <p class={style.copy}>Created with <em-emoji shortcodes=":heart:" set="google"></em-emoji> by <a class={style.copyLink} target="_blank" href="https://github.com/tim0-12432">Tim0_12432</a></p>
        </CardComponent>
    </footer>
);

export default Footer;
