import { Injectable } from '@angular/core';
import { JobsResponse, JobInfo, JobsRequest } from './job.models'
import { Observable } from 'rxjs/Rx'

@Injectable()
export class JobService {

  // Some sample/junk data since endpoint doesn't exist yet
  jobInfos: JobInfo[] = null;

  constructor() {

    this.jobInfos = [
      new JobInfo(42345,"RSS Job","success", "2017-06-05 11:00","2017-06-05 11:15"),
      new JobInfo(42346,"RSS Job","success", "2017-06-05 12:00","2017-06-05 12:15"),
      new JobInfo(42349,"RSS Job","warning", "2017-06-05 13:00","2017-06-05 13:15"),
      new JobInfo(42350,"RSS Job","error", "2017-06-05 14:00","2017-06-05 14:15")
      ];

   }

  get(request:JobsRequest): Observable<JobsResponse> {
    const response = new JobsResponse(this.jobInfos);
    return Observable.of(response);
  }


}
