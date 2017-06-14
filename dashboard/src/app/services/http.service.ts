import { Injectable, OnInit, OnDestroy } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import { AuthService } from './auth.service'
import { Router } from '@angular/router'
import { Subscription } from 'rxjs/Rx'


@Injectable()
export class HttpService {

  constructor(private http: Http, private authService: AuthService, private router: Router) { }

  private handleUnauthorized(err) {
    if (err['status'] != 'undefined' && err['status'] == 403) {
      this.authService.logout()
      this.router.navigate(['/login', 'bounced'])
    }
  }

  post(url: string, body: any) {
    const bodyJson = JSON.stringify(body)
    return this.http.post(url, bodyJson, { headers: this.getHeaders(true) }).do((res) => { }, (err) => this.handleUnauthorized(err))
  }

  get(url: string, params: Array<[string, string]>) {
    return this.http.get(this.constructUrl(url, params), { headers: this.getHeaders(true) }).do((res) => { }, (err) => this.handleUnauthorized(err))
  }

  patch(url: string, body: any) {
    return this.http.patch(url, body, { headers: this.getHeaders(true) }).do((res) => { }, (err) => this.handleUnauthorized(err))
  }

  private constructUrl(url: string, params: Array<[string, string]>): string {
    let urlBuffer = url;
    params.forEach((val, index) => {
      if (index == 0) {
        urlBuffer += '?' + val[0] + '=' + val[1];
      } else {
        urlBuffer += '&' + val[0] + '=' + val[1];
      }
    });

    return urlBuffer;
  }

  private getHeaders(includeToken: boolean): Headers {
    const headers = new Headers();
    headers.append('Accept', 'application/json')
    headers.append('Content-Type', 'application/json')
    //if (includeToken && this.accountInfo != null)
    //headers.append('authToken', this.accountInfo.token)
    return headers;
  }


}
