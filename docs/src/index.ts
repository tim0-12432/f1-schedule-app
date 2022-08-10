import './style/index.css';
import App from './components/app';

declare module "preact/src/jsx" {
    namespace JSXInternal {
        interface IntrinsicElements {
            "em-emoji": EmojiElementAttributes;
        }
    }
}

export default App;
