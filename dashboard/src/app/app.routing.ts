import { Routes, RouterModule } from '@angular/router';
import { WelcomeComponent } from './components/welcome/welcome.component';
import { RecentArticlesComponent } from './components/recent-articles/recent-articles.component'

const APP_ROUTES: Routes = [
    { path: '', redirectTo: '/welcome', pathMatch: 'full' },
    { path: 'welcome', component: WelcomeComponent },
    { path: 'articles', component: RecentArticlesComponent }
];

export const routing = RouterModule.forRoot(APP_ROUTES);