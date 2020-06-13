import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IObjetCredit } from 'app/shared/model/objet-credit.model';

type EntityResponseType = HttpResponse<IObjetCredit>;
type EntityArrayResponseType = HttpResponse<IObjetCredit[]>;

@Injectable({ providedIn: 'root' })
export class ObjetCreditService {
  public resourceUrl = SERVER_API_URL + 'api/objet-credits';

  constructor(protected http: HttpClient) {}

  create(objetCredit: IObjetCredit): Observable<EntityResponseType> {
    return this.http.post<IObjetCredit>(this.resourceUrl, objetCredit, { observe: 'response' });
  }

  update(objetCredit: IObjetCredit): Observable<EntityResponseType> {
    return this.http.put<IObjetCredit>(this.resourceUrl, objetCredit, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IObjetCredit>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IObjetCredit[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
