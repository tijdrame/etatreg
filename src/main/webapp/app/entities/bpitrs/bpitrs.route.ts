import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBpitrs, Bpitrs } from 'app/shared/model/bpitrs.model';
import { BpitrsService } from './bpitrs.service';
import { BpitrsComponent } from './bpitrs.component';
import { BpitrsDetailComponent } from './bpitrs-detail.component';
import { BpitrsUpdateComponent } from './bpitrs-update.component';

@Injectable({ providedIn: 'root' })
export class BpitrsResolve implements Resolve<IBpitrs> {
  constructor(private service: BpitrsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBpitrs> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((bpitrs: HttpResponse<Bpitrs>) => {
          if (bpitrs.body) {
            return of(bpitrs.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Bpitrs());
  }
}

export const bpitrsRoute: Routes = [
  {
    path: '',
    component: BpitrsComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'Bpitrs',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BpitrsDetailComponent,
    resolve: {
      bpitrs: BpitrsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Bpitrs',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BpitrsUpdateComponent,
    resolve: {
      bpitrs: BpitrsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Bpitrs',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BpitrsUpdateComponent,
    resolve: {
      bpitrs: BpitrsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Bpitrs',
    },
    canActivate: [UserRouteAccessService],
  },
];
