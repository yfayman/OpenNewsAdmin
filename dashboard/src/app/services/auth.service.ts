import { Injectable } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';

@Injectable()
export class AuthService {

  constructor(private http: Http) { }

}
