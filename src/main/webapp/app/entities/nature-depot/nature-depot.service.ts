import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { INatureDepot } from 'app/shared/model/nature-depot.model';

type EntityResponseType = HttpResponse<INatureDepot>;
type EntityArrayResponseType = HttpResponse<INatureDepot[]>;

@Injectable({ providedIn: 'root' })
export class NatureDepotService {
  public resourceUrl = SERVER_API_URL + 'api/nature-depots';

  constructor(protected http: HttpClient) {}

  create(natureDepot: INatureDepot): Observable<EntityResponseType> {
    return this.http.post<INatureDepot>(this.resourceUrl, natureDepot, { observe: 'response' });
  }

  update(natureDepot: INatureDepot): Observable<EntityResponseType> {
    return this.http.put<INatureDepot>(this.resourceUrl, natureDepot, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<INatureDepot>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<INatureDepot[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
