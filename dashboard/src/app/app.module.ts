import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { AuthService } from './services/auth.service'
import { HttpService } from './services/http.service'
import { AppComponent } from './app.component';
import {routing} from './app.routing';
import { HeaderComponent } from './header.component';
import { SideBarComponent } from './side-bar.component';
import { WelcomeComponent } from './components/welcome/welcome.component';
import { RecentArticlesComponent } from './components/recent-articles/recent-articles.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SideBarComponent,
    WelcomeComponent,
    RecentArticlesComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    routing
  ],
  providers: [AuthService, HttpService],
  bootstrap: [AppComponent]
})
export class AppModule { }
