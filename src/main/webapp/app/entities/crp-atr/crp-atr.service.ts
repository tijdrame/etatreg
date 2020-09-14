import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICrpAtr } from 'app/shared/model/crp-atr.model';

type EntityResponseType = HttpResponse<ICrpAtr>;
type EntityArrayResponseType = HttpResponse<ICrpAtr[]>;

@Injectable({ providedIn: 'root' })
export class CrpAtrService {
  public resourceUrl = SERVER_API_URL + 'api/crp-atrs';

  constructor(protected http: HttpClient) {}

  create(crpAtr: ICrpAtr): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(crpAtr);
    return this.http
      .post<ICrpAtr>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(crpAtr: ICrpAtr): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(crpAtr);
    return this.http
      .put<ICrpAtr>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICrpAtr>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICrpAtr[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(crpAtr: ICrpAtr): ICrpAtr {
    const copy: ICrpAtr = Object.assign({}, crpAtr, {
      demAtr: crpAtr.demAtr && crpAtr.demAtr.isValid() ? crpAtr.demAtr.format(DATE_FORMAT) : undefined,
      datope: crpAtr.datope && crpAtr.datope.isValid() ? crpAtr.datope.format(DATE_FORMAT) : undefined,
      dateArrete: crpAtr.dateArrete && crpAtr.dateArrete.isValid() ? crpAtr.dateArrete.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.demAtr = res.body.demAtr ? moment(res.body.demAtr) : undefined;
      res.body.datope = res.body.datope ? moment(res.body.datope) : undefined;
      res.body.dateArrete = res.body.dateArrete ? moment(res.body.dateArrete) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((crpAtr: ICrpAtr) => {
        crpAtr.demAtr = crpAtr.demAtr ? moment(crpAtr.demAtr) : undefined;
        crpAtr.datope = crpAtr.datope ? moment(crpAtr.datope) : undefined;
        crpAtr.dateArrete = crpAtr.dateArrete ? moment(crpAtr.dateArrete) : undefined;
      });
    }
    return res;
  }
}
