import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { INatureDebiteur } from 'app/shared/model/nature-debiteur.model';

type EntityResponseType = HttpResponse<INatureDebiteur>;
type EntityArrayResponseType = HttpResponse<INatureDebiteur[]>;

@Injectable({ providedIn: 'root' })
export class NatureDebiteurService {
  public resourceUrl = SERVER_API_URL + 'api/nature-debiteurs';

  constructor(protected http: HttpClient) {}

  create(natureDebiteur: INatureDebiteur): Observable<EntityResponseType> {
    return this.http.post<INatureDebiteur>(this.resourceUrl, natureDebiteur, { observe: 'response' });
  }

  update(natureDebiteur: INatureDebiteur): Observable<EntityResponseType> {
    return this.http.put<INatureDebiteur>(this.resourceUrl, natureDebiteur, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<INatureDebiteur>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<INatureDebiteur[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
