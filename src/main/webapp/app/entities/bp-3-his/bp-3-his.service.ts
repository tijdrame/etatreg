import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBp3His } from 'app/shared/model/bp-3-his.model';

type EntityResponseType = HttpResponse<IBp3His>;
type EntityArrayResponseType = HttpResponse<IBp3His[]>;

@Injectable({ providedIn: 'root' })
export class Bp3HisService {
  public resourceUrl = SERVER_API_URL + 'api/bp-3-his';

  constructor(protected http: HttpClient) {}

  create(bp3His: IBp3His): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(bp3His);
    return this.http
      .post<IBp3His>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(bp3His: IBp3His): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(bp3His);
    return this.http
      .put<IBp3His>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IBp3His>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IBp3His[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(bp3His: IBp3His): IBp3His {
    const copy: IBp3His = Object.assign({}, bp3His, {
      dco: bp3His.dco && bp3His.dco.isValid() ? bp3His.dco.format(DATE_FORMAT) : undefined,
      dateArrete: bp3His.dateArrete && bp3His.dateArrete.isValid() ? bp3His.dateArrete.format(DATE_FORMAT) : undefined,
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
      res.body.forEach((bp3His: IBp3His) => {
        bp3His.dco = bp3His.dco ? moment(bp3His.dco) : undefined;
        bp3His.dateArrete = bp3His.dateArrete ? moment(bp3His.dateArrete) : undefined;
      });
    }
    return res;
  }
}
