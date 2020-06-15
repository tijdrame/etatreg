import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IChargement, Chargement } from 'app/shared/model/chargement.model';
import { ChargementService } from './chargement.service';
import { ChargementComponent } from './chargement.component';
import { ChargementDetailComponent } from './chargement-detail.component';
import { ChargementUpdateComponent } from './chargement-update.component';

@Injectable({ providedIn: 'root' })
export class ChargementResolve implements Resolve<IChargement> {
  constructor(private service: ChargementService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IChargement> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((chargement: HttpResponse<Chargement>) => {
          if (chargement.body) {
            return of(chargement.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Chargement());
  }
}

export const chargementRoute: Routes = [
  {
    path: '',
    component: ChargementComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'Chargements',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ChargementDetailComponent,
    resolve: {
      chargement: ChargementResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Chargements',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ChargementUpdateComponent,
    resolve: {
      chargement: ChargementResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Chargements',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ChargementUpdateComponent,
    resolve: {
      chargement: ChargementResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Chargements',
    },
    canActivate: [UserRouteAccessService],
  },
];
