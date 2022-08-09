import { h } from 'preact';
import Card from 'preact-material-components/Card';
import 'preact-material-components/Card/style.css';
import style from './style.css';

interface Props {
    children: JSX.Element | JSX.Element[];
}

const CardComponent = ({ children }: Props) => (
    <Card class={style.card}>
        {children}
    </Card>
);

export default CardComponent;