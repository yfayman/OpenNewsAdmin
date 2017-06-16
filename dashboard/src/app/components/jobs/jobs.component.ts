import { Component, OnInit, OnDestroy } from '@angular/core';
import { JobService } from '../../services/job.service'
import { Observable, Subscription } from 'rxjs/Rx'
import { Subject } from "rxjs/Subject"
import { JobInfo, JobsRequest } from '../../services/job.models'

@Component({
  selector: 'app-jobs',
  templateUrl: './jobs.component.html',
  styleUrls: ['./jobs.component.css']
})
export class JobsComponent implements OnInit {

  private ngUnsubscribe: Subject<void> = new Subject<void>();

  recentJobsObs: Observable<JobInfo[]>

  constructor(private jobService: JobService) { }

  ngOnInit() {
    const req = new JobsRequest();
    this.recentJobsObs = this.jobService.get(req).map((res) => res.jobs)
    this.recentJobsObs.takeUntil(this.ngUnsubscribe).subscribe((sub) => { });
  }

}
