import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IChargement } from 'app/shared/model/chargement.model';

type EntityResponseType = HttpResponse<IChargement>;
type EntityArrayResponseType = HttpResponse<IChargement[]>;

@Injectable({ providedIn: 'root' })
export class ChargementService {
  public resourceUrl = SERVER_API_URL + 'api/chargements';

  constructor(protected http: HttpClient) {}

  create(chargement: IChargement): Observable<EntityResponseType> {
    return this.http.post<IChargement>(this.resourceUrl, chargement, { observe: 'response' });
  }

  update(chargement: IChargement): Observable<EntityResponseType> {
    return this.http.put<IChargement>(this.resourceUrl, chargement, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IChargement>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IChargement[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
