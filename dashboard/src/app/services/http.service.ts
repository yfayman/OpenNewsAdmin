import { Injectable, OnInit, OnDestroy } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import { AuthService } from './auth.service'
import {Router} from '@angular/router'

@Injectable()
export class HttpService {

  constructor(private http: Http, private authService: AuthService, private router:Router) { }

}
