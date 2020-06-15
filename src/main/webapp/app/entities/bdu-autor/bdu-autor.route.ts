import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBduAutor, BduAutor } from 'app/shared/model/bdu-autor.model';
import { BduAutorService } from './bdu-autor.service';
import { BduAutorComponent } from './bdu-autor.component';
import { BduAutorDetailComponent } from './bdu-autor-detail.component';
import { BduAutorUpdateComponent } from './bdu-autor-update.component';

@Injectable({ providedIn: 'root' })
export class BduAutorResolve implements Resolve<IBduAutor> {
  constructor(private service: BduAutorService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBduAutor> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((bduAutor: HttpResponse<BduAutor>) => {
          if (bduAutor.body) {
            return of(bduAutor.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new BduAutor());
  }
}

export const bduAutorRoute: Routes = [
  {
    path: '',
    component: BduAutorComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'BduAutors',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BduAutorDetailComponent,
    resolve: {
      bduAutor: BduAutorResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'BduAutors',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BduAutorUpdateComponent,
    resolve: {
      bduAutor: BduAutorResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'BduAutors',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BduAutorUpdateComponent,
    resolve: {
      bduAutor: BduAutorResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'BduAutors',
    },
    canActivate: [UserRouteAccessService],
  },
];
