
export function createHashHistory() {
    let url = window.location + "#/";
    return {
        location: url,
        push(path: string) {
            url += path;
        },
        replace(path: string) {
            url = path;
        },
        listen(action: any): () => void {
            return action(url);
        }
    };
}