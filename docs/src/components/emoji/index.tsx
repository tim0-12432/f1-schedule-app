import { h } from 'preact';
import heart from '../../assets/emojis/heart.png';
import blackFlag from '../../assets/emojis/black_flag.png';
import whiteFlag from '../../assets/emojis/white_flag.png';
import redFlag from '../../assets/emojis/red_flag.png';
import crossedFlags from '../../assets/emojis/crossed_flags.png';
import checkeredFlag from '../../assets/emojis/checkered_flag.png';
import deFlag from '../../assets/emojis/de_flag.png';
import enFlag from '../../assets/emojis/gb_flag.png';
import pointer from '../../assets/emojis/point.png';
import style from './style.css';

interface Props {
    emojicode: "heart" | "finished" | "retired" | "finished_white" | "damage" | "accident" | "finger_point" | "en" | "de";
    class?: any;
}

export function Emoji(props: Props) {
    let source = null;
    switch (props.emojicode) {
        case "heart":
            source = heart;
            break;
        case "finished":
            source = checkeredFlag;
            break;
        case "retired":
            source = crossedFlags;
            break;
        case "finished_white":
            source = whiteFlag;
            break;
        case "damage":
            source = blackFlag;
            break;
        case "accident":
            source = redFlag;
            break;
        case "finger_point":
            source = pointer;
            break;
        case "en":
            source = enFlag;
            break;
        case "de":
            source = deFlag;
            break;
    }
    return source === null ? null : (
        <img src={source} class={props.class === undefined ? style.img : props.class} />
    );
}

export default Emoji;