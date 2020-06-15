import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBduCdb, BduCdb } from 'app/shared/model/bdu-cdb.model';
import { BduCdbService } from './bdu-cdb.service';
import { BduCdbComponent } from './bdu-cdb.component';
import { BduCdbDetailComponent } from './bdu-cdb-detail.component';
import { BduCdbUpdateComponent } from './bdu-cdb-update.component';

@Injectable({ providedIn: 'root' })
export class BduCdbResolve implements Resolve<IBduCdb> {
  constructor(private service: BduCdbService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBduCdb> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((bduCdb: HttpResponse<BduCdb>) => {
          if (bduCdb.body) {
            return of(bduCdb.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new BduCdb());
  }
}

export const bduCdbRoute: Routes = [
  {
    path: '',
    component: BduCdbComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'BduCdbs',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BduCdbDetailComponent,
    resolve: {
      bduCdb: BduCdbResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'BduCdbs',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BduCdbUpdateComponent,
    resolve: {
      bduCdb: BduCdbResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'BduCdbs',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BduCdbUpdateComponent,
    resolve: {
      bduCdb: BduCdbResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'BduCdbs',
    },
    canActivate: [UserRouteAccessService],
  },
];
