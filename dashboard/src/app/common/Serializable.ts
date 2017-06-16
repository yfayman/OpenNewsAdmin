// Going to put this in common for now
export interface Serializable {
    deserialize(input: any):void
    serialize():any
}