// import { PureComponent } from 'preact/compat'

declare module "*.css" {
    const mapping: Record<string, string>;
    export default mapping;
}

declare module "*.jpg" {
    const image: string;
    export default image;
}

declare module "*.png" {
    const image: string;
    export default image;
}

declare module "*.gif" {
    const image: string;
    export default image;
}

declare module "@emoji-mart/data" {
    export const data: Record<string, string>;
}

declare module "emoji-mart" {
    export function init (data: any): void;
    export default init;
}

declare interface EmojiElementAttributes extends preact.JSX.HTMLAttributes<HTMLElement> {
    shortcodes?: string;
    set?: string;
    id?: string;
}