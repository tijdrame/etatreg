import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBduDepot, BduDepot } from 'app/shared/model/bdu-depot.model';
import { BduDepotService } from './bdu-depot.service';
import { BduDepotComponent } from './bdu-depot.component';
import { BduDepotDetailComponent } from './bdu-depot-detail.component';
import { BduDepotUpdateComponent } from './bdu-depot-update.component';

@Injectable({ providedIn: 'root' })
export class BduDepotResolve implements Resolve<IBduDepot> {
  constructor(private service: BduDepotService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBduDepot> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((bduDepot: HttpResponse<BduDepot>) => {
          if (bduDepot.body) {
            return of(bduDepot.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new BduDepot());
  }
}

export const bduDepotRoute: Routes = [
  {
    path: '',
    component: BduDepotComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'BduDepots',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BduDepotDetailComponent,
    resolve: {
      bduDepot: BduDepotResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'BduDepots',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BduDepotUpdateComponent,
    resolve: {
      bduDepot: BduDepotResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'BduDepots',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BduDepotUpdateComponent,
    resolve: {
      bduDepot: BduDepotResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'BduDepots',
    },
    canActivate: [UserRouteAccessService],
  },
];
