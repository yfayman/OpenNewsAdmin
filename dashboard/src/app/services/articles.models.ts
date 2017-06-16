import { Serializable } from '../common/Serializable'

export class Article implements Serializable {
    constructor(
        public id: number,
        public url: string,
        public title: string,
        public html: string,
        public status: string,
        public shortDescription: string
    ) { }

    deserialize(input: any): void {

    }

    serialize(): any {
        return null;
    }

}

export class ArticlesRequest {

}

export class ArticlesResponse {
    constructor(public articles: Article[]) { }
}