
export function createHashHistory() {
    let url = window.location + "#/";
    return {
        location: {
            pathname: url,
            search: "",
            hash: "",
            state: null,
            key: null
        },
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