import { h } from 'preact';
import Card from 'preact-material-components/Card';
import 'preact-material-components/Card/style.css';
import style from './style.css';

interface Props {
    children: JSX.Element | JSX.Element[];
    styling?: any;
}

const CardComponent = ({ children, styling }: Props) => (
    <Card class={style.card} style={styling === undefined ? {} : styling}>
        {children}
    </Card>
);

export default CardComponent;