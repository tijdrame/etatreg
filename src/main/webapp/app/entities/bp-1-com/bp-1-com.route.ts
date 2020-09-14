import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBp1Com, Bp1Com } from 'app/shared/model/bp-1-com.model';
import { Bp1ComService } from './bp-1-com.service';
import { Bp1ComComponent } from './bp-1-com.component';
import { Bp1ComDetailComponent } from './bp-1-com-detail.component';
import { Bp1ComUpdateComponent } from './bp-1-com-update.component';

@Injectable({ providedIn: 'root' })
export class Bp1ComResolve implements Resolve<IBp1Com> {
  constructor(private service: Bp1ComService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBp1Com> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((bp1Com: HttpResponse<Bp1Com>) => {
          if (bp1Com.body) {
            return of(bp1Com.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Bp1Com());
  }
}

export const bp1ComRoute: Routes = [
  {
    path: '',
    component: Bp1ComComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'Bp1Coms',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: Bp1ComDetailComponent,
    resolve: {
      bp1Com: Bp1ComResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Bp1Coms',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: Bp1ComUpdateComponent,
    resolve: {
      bp1Com: Bp1ComResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Bp1Coms',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: Bp1ComUpdateComponent,
    resolve: {
      bp1Com: Bp1ComResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Bp1Coms',
    },
    canActivate: [UserRouteAccessService],
  },
];
