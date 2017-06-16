import { Routes, RouterModule } from '@angular/router';
import { WelcomeComponent } from './components/welcome/welcome.component';
import { RecentArticlesComponent } from './components/recent-articles/recent-articles.component'
import { JobsComponent } from './components/jobs/jobs.component'

const APP_ROUTES: Routes = [
    { path: '', redirectTo: '/welcome', pathMatch: 'full' },
    { path: 'welcome', component: WelcomeComponent },
    { path: 'articles', component: RecentArticlesComponent },
    { path: 'jobs', component: JobsComponent }
];

export const routing = RouterModule.forRoot(APP_ROUTES);