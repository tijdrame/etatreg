import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBp1His } from 'app/shared/model/bp-1-his.model';

type EntityResponseType = HttpResponse<IBp1His>;
type EntityArrayResponseType = HttpResponse<IBp1His[]>;

@Injectable({ providedIn: 'root' })
export class Bp1HisService {
  public resourceUrl = SERVER_API_URL + 'api/bp-1-his';

  constructor(protected http: HttpClient) {}

  create(bp1His: IBp1His): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(bp1His);
    return this.http
      .post<IBp1His>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(bp1His: IBp1His): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(bp1His);
    return this.http
      .put<IBp1His>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IBp1His>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IBp1His[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(bp1His: IBp1His): IBp1His {
    const copy: IBp1His = Object.assign({}, bp1His, {
      dco: bp1His.dco && bp1His.dco.isValid() ? bp1His.dco.format(DATE_FORMAT) : undefined,
      dateArrete: bp1His.dateArrete && bp1His.dateArrete.isValid() ? bp1His.dateArrete.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dco = res.body.dco ? moment(res.body.dco) : undefined;
      res.body.dateArrete = res.body.dateArrete ? moment(res.body.dateArrete) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((bp1His: IBp1His) => {
        bp1His.dco = bp1His.dco ? moment(bp1His.dco) : undefined;
        bp1His.dateArrete = bp1His.dateArrete ? moment(bp1His.dateArrete) : undefined;
      });
    }
    return res;
  }
}
