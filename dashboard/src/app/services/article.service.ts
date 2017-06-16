import { Injectable } from '@angular/core';
import { HttpService } from './http.service'
import { ArticlesRequest, ArticlesResponse, Article } from './articles.models'
import { Observable } from 'rxjs/Rx'

@Injectable()
export class ArticleService {

  // Some sample/junk data since endpoint doesn't exist yet
  articles: Article[] = null;

  constructor() {

    this.articles = [
      new Article(1,"http://www.breitbart.com/a","NOT FAKE NEWS","<h1>Filler</h1>","APPROVED","Does it really matter"),
      new Article(2,"http://www.breitbart.com/b","NOT FAKE NEWS","<h1>Filler</h1>","REJECTED","Does it really matter"),
      new Article(2,"http://www.nytimes.com/b","BREITBART IS FAKE NEWS","<h1>Filler</h1>","APPROVED","Does it really matter")
    ];

  }

  get(request: ArticlesRequest): Observable<ArticlesResponse> {
    return Observable.of(new ArticlesResponse(this.articles));
  }


}
