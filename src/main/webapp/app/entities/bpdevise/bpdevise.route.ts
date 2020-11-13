import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBpdevise, Bpdevise } from 'app/shared/model/bpdevise.model';
import { BpdeviseService } from './bpdevise.service';
import { BpdeviseComponent } from './bpdevise.component';
import { BpdeviseDetailComponent } from './bpdevise-detail.component';
import { BpdeviseUpdateComponent } from './bpdevise-update.component';

@Injectable({ providedIn: 'root' })
export class BpdeviseResolve implements Resolve<IBpdevise> {
  constructor(private service: BpdeviseService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBpdevise> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((bpdevise: HttpResponse<Bpdevise>) => {
          if (bpdevise.body) {
            return of(bpdevise.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Bpdevise());
  }
}

export const bpdeviseRoute: Routes = [
  {
    path: '',
    component: BpdeviseComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'Bpdevises',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BpdeviseDetailComponent,
    resolve: {
      bpdevise: BpdeviseResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Bpdevises',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BpdeviseUpdateComponent,
    resolve: {
      bpdevise: BpdeviseResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Bpdevises',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BpdeviseUpdateComponent,
    resolve: {
      bpdevise: BpdeviseResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Bpdevises',
    },
    canActivate: [UserRouteAccessService],
  },
];
