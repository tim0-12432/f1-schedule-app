import { h } from 'preact';
import CardComponent from '../card';
import Emoji from '../emoji';
import style from './style.css';

const Footer = () => (
    <footer class={style.footer}>
        <CardComponent>
            <p class={style.copy}>Created with <Emoji emojicode="heart" /> by <a class={style.copyLink} target="_blank" href="https://github.com/tim0-12432">Tim0_12432</a></p>
            <p class={style.disclaimer}>This page is just a concept and for case-study purposes!</p>
        </CardComponent>
    </footer>
);

export default Footer;
