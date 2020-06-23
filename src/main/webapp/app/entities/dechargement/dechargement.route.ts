import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDechargement, Dechargement } from 'app/shared/model/dechargement.model';
import { DechargementService } from './dechargement.service';
import { DechargementComponent } from './dechargement.component';
import { DechargementDetailComponent } from './dechargement-detail.component';
import { DechargementUpdateComponent } from './dechargement-update.component';

@Injectable({ providedIn: 'root' })
export class DechargementResolve implements Resolve<IDechargement> {
  constructor(private service: DechargementService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDechargement> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((dechargement: HttpResponse<Dechargement>) => {
          if (dechargement.body) {
            return of(dechargement.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Dechargement());
  }
}

export const dechargementRoute: Routes = [
  {
    path: '',
    component: DechargementComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'Dechargements',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DechargementDetailComponent,
    resolve: {
      dechargement: DechargementResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Dechargements',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'chargementCDP',
    component: DechargementUpdateComponent,
    resolve: {
      dechargement: DechargementResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Dechargements',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DechargementUpdateComponent,
    resolve: {
      dechargement: DechargementResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Dechargements',
    },
    canActivate: [UserRouteAccessService],
  },
];
