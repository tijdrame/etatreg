import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBp1His, Bp1His } from 'app/shared/model/bp-1-his.model';
import { Bp1HisService } from './bp-1-his.service';
import { Bp1HisComponent } from './bp-1-his.component';
import { Bp1HisDetailComponent } from './bp-1-his-detail.component';
import { Bp1HisUpdateComponent } from './bp-1-his-update.component';

@Injectable({ providedIn: 'root' })
export class Bp1HisResolve implements Resolve<IBp1His> {
  constructor(private service: Bp1HisService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBp1His> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((bp1His: HttpResponse<Bp1His>) => {
          if (bp1His.body) {
            return of(bp1His.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Bp1His());
  }
}

export const bp1HisRoute: Routes = [
  {
    path: '',
    component: Bp1HisComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'Bp1His',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: Bp1HisDetailComponent,
    resolve: {
      bp1His: Bp1HisResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Bp1His',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: Bp1HisUpdateComponent,
    resolve: {
      bp1His: Bp1HisResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Bp1His',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: Bp1HisUpdateComponent,
    resolve: {
      bp1His: Bp1HisResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Bp1His',
    },
    canActivate: [UserRouteAccessService],
  },
];
