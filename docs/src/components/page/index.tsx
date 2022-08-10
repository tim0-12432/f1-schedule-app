import { h } from 'preact';
import style from './style.css';

interface Props {
    children: JSX.Element | JSX.Element[];
}

const PageContainer = ({ children }: Props) => (
    <div class={style.page}>
        {children}
    </div>
);

export default PageContainer;