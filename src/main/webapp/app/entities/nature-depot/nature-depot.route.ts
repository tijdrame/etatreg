import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { INatureDepot, NatureDepot } from 'app/shared/model/nature-depot.model';
import { NatureDepotService } from './nature-depot.service';
import { NatureDepotComponent } from './nature-depot.component';
import { NatureDepotDetailComponent } from './nature-depot-detail.component';
import { NatureDepotUpdateComponent } from './nature-depot-update.component';

@Injectable({ providedIn: 'root' })
export class NatureDepotResolve implements Resolve<INatureDepot> {
  constructor(private service: NatureDepotService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<INatureDepot> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((natureDepot: HttpResponse<NatureDepot>) => {
          if (natureDepot.body) {
            return of(natureDepot.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new NatureDepot());
  }
}

export const natureDepotRoute: Routes = [
  {
    path: '',
    component: NatureDepotComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'NatureDepots',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: NatureDepotDetailComponent,
    resolve: {
      natureDepot: NatureDepotResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'NatureDepots',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: NatureDepotUpdateComponent,
    resolve: {
      natureDepot: NatureDepotResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'NatureDepots',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: NatureDepotUpdateComponent,
    resolve: {
      natureDepot: NatureDepotResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'NatureDepots',
    },
    canActivate: [UserRouteAccessService],
  },
];
