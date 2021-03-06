import { Component, OnInit, OnDestroy } from '@angular/core';
import { /* HttpHeaders, */ HttpResponse } from '@angular/common/http';
import { ActivatedRoute, /* ParamMap, */ Router /* , Data */ } from '@angular/router';
import { Subscription /* , combineLatest */ } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IDechargement } from 'app/shared/model/dechargement.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { DechargementService } from './dechargement.service';
// import { DechargementDeleteDialogComponent } from './dechargement-delete-dialog.component';
import { IPeriode } from 'app/shared/model/periode.model';
import { PeriodeService } from '../periode/periode.service';
import { IFilesInfos } from 'app/shared/model/files-infos.model';
import { FilesInfosService } from '../files-infos/files-infos.service';

@Component({
  selector: 'jhi-dechargement',
  templateUrl: './dechargement.component.html',
})
export class DechargementComponent implements OnInit, OnDestroy {
  dechargements?: IDechargement[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;
  periodes: IPeriode[] = [];
  codeFichier = '';
  periode: IPeriode = {};
  dateGen = '';
  version = '';
  filesInfos: IFilesInfos[] = [];
  isLoading = false;
  fileInfo: IFilesInfos = {};

  constructor(
    protected dechargementService: DechargementService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected periodeService: PeriodeService,
    protected filesInfosService: FilesInfosService
  ) {}

  /* loadPage(page?: number, dontNavigate?: boolean): void {
    const pageToLoad: number = page || this.page || 1;

    this.dechargementService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe(
        (res: HttpResponse<IDechargement[]>) => this.onSuccess(res.body, res.headers, pageToLoad, !dontNavigate),
        () => this.onError()
      );
  } */

  ngOnInit(): void {
    // this.handleNavigation();
    // this.registerChangeInDechargements();
    this.periodeService
      .query({
        page: 0,
        size: 50,
      })
      .subscribe((res: HttpResponse<IPeriode[]>) => (this.periodes = res.body!));
    this.filesInfosService
      .queryBis({
        page: 0,
        size: 50,
        code: 'CDB',
      })
      .subscribe((res: HttpResponse<IFilesInfos[]>) => (this.filesInfos = res.body!));
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  generate(): void {
    alert('date=' + this.dateGen + ' codeFic=' + this.fileInfo?.codeFile + ' period=' + this.periode?.code + ' version=' + this.version);
  }

  /* protected handleNavigation(): void {
    combineLatest(this.activatedRoute.data, this.activatedRoute.queryParamMap, (data: Data, params: ParamMap) => {
      const page = params.get('page');
      const pageNumber = page !== null ? +page : 1;
      const sort = (params.get('sort') ?? data['defaultSort']).split(',');
      const predicate = sort[0];
      const ascending = sort[1] === 'asc';
      if (pageNumber !== this.page || predicate !== this.predicate || ascending !== this.ascending) {
        this.predicate = predicate;
        this.ascending = ascending;
        this.loadPage(pageNumber, true);
      }
    }).subscribe();
  }

  

  trackId(index: number, item: IDechargement): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInDechargements(): void {
    this.eventSubscriber = this.eventManager.subscribe('dechargementListModification', () => this.loadPage());
  }

  delete(dechargement: IDechargement): void {
    const modalRef = this.modalService.open(DechargementDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.dechargement = dechargement;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IDechargement[] | null, headers: HttpHeaders, page: number, navigate: boolean): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    if (navigate) {
      this.router.navigate(['/dechargement'], {
        queryParams: {
          page: this.page,
          size: this.itemsPerPage,
          sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc'),
        },
      });
    }
    this.dechargements = data || [];
    this.ngbPaginationPage = this.page;
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page ?? 1;
  } */
}
