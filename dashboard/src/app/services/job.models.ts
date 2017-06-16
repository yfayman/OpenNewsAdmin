import { Serializable } from '../common/Serializable'

export class JobInfo implements Serializable {
    constructor
        (
        public uid: number,
        public name: string,
        public status: String,
        public startDate: String,
        public endDate: String
        ) { }

    deserialize(input: any): void {

    }
    serialize(): any {
        return null;
    }

}

export class JobsRequest {

}

export class JobsResponse {
    constructor(public jobs: JobInfo[]) { }
}

