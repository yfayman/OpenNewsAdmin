import { Component, OnInit } from '@angular/core';
import { ArticleService } from '../../services/article.service'
import { Article, ArticlesRequest } from '../../services/articles.models'
import { Observable } from 'rxjs/Rx'
import { Subject } from "rxjs/Subject"


@Component({
  selector: 'app-recent-articles',
  templateUrl: './recent-articles.component.html',
  styleUrls: ['./recent-articles.component.css']
})
export class RecentArticlesComponent implements OnInit {

  private ngUnsubscribe: Subject<void> = new Subject<void>();

  constructor(private articleService: ArticleService) { }

  articlesObs: Observable<Article[]>;

  ngOnInit() {
    const request = new ArticlesRequest();
    this.articlesObs = this.articleService.get(request).map( (req)=> req.articles )
    this.articlesObs.takeUntil(this.ngUnsubscribe).subscribe((sub) => { });
  }

}
