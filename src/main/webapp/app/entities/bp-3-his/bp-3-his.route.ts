import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBp3His, Bp3His } from 'app/shared/model/bp-3-his.model';
import { Bp3HisService } from './bp-3-his.service';
import { Bp3HisComponent } from './bp-3-his.component';
import { Bp3HisDetailComponent } from './bp-3-his-detail.component';
import { Bp3HisUpdateComponent } from './bp-3-his-update.component';

@Injectable({ providedIn: 'root' })
export class Bp3HisResolve implements Resolve<IBp3His> {
  constructor(private service: Bp3HisService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBp3His> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((bp3His: HttpResponse<Bp3His>) => {
          if (bp3His.body) {
            return of(bp3His.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Bp3His());
  }
}

export const bp3HisRoute: Routes = [
  {
    path: '',
    component: Bp3HisComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'Bp3His',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: Bp3HisDetailComponent,
    resolve: {
      bp3His: Bp3HisResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Bp3His',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: Bp3HisUpdateComponent,
    resolve: {
      bp3His: Bp3HisResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Bp3His',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: Bp3HisUpdateComponent,
    resolve: {
      bp3His: Bp3HisResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Bp3His',
    },
    canActivate: [UserRouteAccessService],
  },
];
