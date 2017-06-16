import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { AuthService } from './services/auth.service'
import { HttpService } from './services/http.service'
import {ArticleService} from './services/article.service'
import { JobService } from './services/job.service'
import { AppComponent } from './app.component';
import {routing} from './app.routing';
import { HeaderComponent } from './header.component';
import { SideBarComponent } from './side-bar.component';
import { WelcomeComponent } from './components/welcome/welcome.component';
import { RecentArticlesComponent } from './components/recent-articles/recent-articles.component';
import { JobsComponent } from './components/jobs/jobs.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SideBarComponent,
    WelcomeComponent,
    RecentArticlesComponent,
    JobsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    routing
  ],
  providers: [AuthService, HttpService, JobService, ArticleService],
  bootstrap: [AppComponent]
})
export class AppModule { }
