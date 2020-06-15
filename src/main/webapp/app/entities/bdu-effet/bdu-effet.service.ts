import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBduEffet } from 'app/shared/model/bdu-effet.model';

type EntityResponseType = HttpResponse<IBduEffet>;
type EntityArrayResponseType = HttpResponse<IBduEffet[]>;

@Injectable({ providedIn: 'root' })
export class BduEffetService {
  public resourceUrl = SERVER_API_URL + 'api/bdu-effets';

  constructor(protected http: HttpClient) {}

  create(bduEffet: IBduEffet): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(bduEffet);
    return this.http
      .post<IBduEffet>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(bduEffet: IBduEffet): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(bduEffet);
    return this.http
      .put<IBduEffet>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IBduEffet>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IBduEffet[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(bduEffet: IBduEffet): IBduEffet {
    const copy: IBduEffet = Object.assign({}, bduEffet, {
      dateEscompte: bduEffet.dateEscompte && bduEffet.dateEscompte.isValid() ? bduEffet.dateEscompte.format(DATE_FORMAT) : undefined,
      dateEcheance: bduEffet.dateEcheance && bduEffet.dateEcheance.isValid() ? bduEffet.dateEcheance.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateEscompte = res.body.dateEscompte ? moment(res.body.dateEscompte) : undefined;
      res.body.dateEcheance = res.body.dateEcheance ? moment(res.body.dateEcheance) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((bduEffet: IBduEffet) => {
        bduEffet.dateEscompte = bduEffet.dateEscompte ? moment(bduEffet.dateEscompte) : undefined;
        bduEffet.dateEcheance = bduEffet.dateEcheance ? moment(bduEffet.dateEcheance) : undefined;
      });
    }
    return res;
  }
}
