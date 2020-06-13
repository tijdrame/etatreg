import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBp2Infos } from 'app/shared/model/bp-2-infos.model';

type EntityResponseType = HttpResponse<IBp2Infos>;
type EntityArrayResponseType = HttpResponse<IBp2Infos[]>;

@Injectable({ providedIn: 'root' })
export class Bp2InfosService {
  public resourceUrl = SERVER_API_URL + 'api/bp-2-infos';

  constructor(protected http: HttpClient) {}

  create(bp2Infos: IBp2Infos): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(bp2Infos);
    return this.http
      .post<IBp2Infos>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(bp2Infos: IBp2Infos): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(bp2Infos);
    return this.http
      .put<IBp2Infos>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IBp2Infos>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IBp2Infos[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(bp2Infos: IBp2Infos): IBp2Infos {
    const copy: IBp2Infos = Object.assign({}, bp2Infos, {
      dateChargement: bp2Infos.dateChargement && bp2Infos.dateChargement.isValid() ? bp2Infos.dateChargement.toJSON() : undefined,
      dateDechargement: bp2Infos.dateDechargement && bp2Infos.dateDechargement.isValid() ? bp2Infos.dateDechargement.toJSON() : undefined,
      passifCliCptVue: bp2Infos.passifCliCptVue && bp2Infos.passifCliCptVue.isValid() ? bp2Infos.passifCliCptVue.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateChargement = res.body.dateChargement ? moment(res.body.dateChargement) : undefined;
      res.body.dateDechargement = res.body.dateDechargement ? moment(res.body.dateDechargement) : undefined;
      res.body.passifCliCptVue = res.body.passifCliCptVue ? moment(res.body.passifCliCptVue) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((bp2Infos: IBp2Infos) => {
        bp2Infos.dateChargement = bp2Infos.dateChargement ? moment(bp2Infos.dateChargement) : undefined;
        bp2Infos.dateDechargement = bp2Infos.dateDechargement ? moment(bp2Infos.dateDechargement) : undefined;
        bp2Infos.passifCliCptVue = bp2Infos.passifCliCptVue ? moment(bp2Infos.passifCliCptVue) : undefined;
      });
    }
    return res;
  }
}
