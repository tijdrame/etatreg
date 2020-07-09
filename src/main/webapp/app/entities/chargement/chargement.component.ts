import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, ParamMap, Router, Data } from '@angular/router';
import { Subscription, combineLatest } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IChargement } from 'app/shared/model/chargement.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { ChargementService } from './chargement.service';
import { ChargementDeleteDialogComponent } from './chargement-delete-dialog.component';
import { IPeriode } from 'app/shared/model/periode.model';
import { PeriodeService } from '../periode/periode.service';
import { FilesInfosService } from '../files-infos/files-infos.service';
import { IFilesInfos } from 'app/shared/model/files-infos.model';

@Component({
  selector: 'jhi-chargement',
  templateUrl: './chargement.component.html',
})
export class ChargementComponent implements OnInit, OnDestroy {
  chargements?: IChargement[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;
  periodes: IPeriode[] = [];
  codeFichier = "";
  periode : IPeriode = {};
  filesInfos: IFilesInfos[] = [];
  fileInfo : IFilesInfos = {};
  dateGen = '';
  version = "";

  constructor(
    protected chargementService: ChargementService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected periodeService: PeriodeService,
    protected filesInfosService: FilesInfosService
  ) {}

  

  ngOnInit(): void {
    // this.handleNavigation();
    // this.registerChangeInChargements();
    this.periodeService
      .query({
        page: 0,
        size: 50,
        sort: this.sort(),
      })
      .subscribe(
        (res: HttpResponse<IPeriode[]>) => this.periodes = res.body!,
        () => this.onError()
      );
      this.filesInfosService
      .queryBis({
        page: 0,
        size: 50,
        sort: this.sort(),
        code: 'BPR'
      })
      .subscribe(
        (res: HttpResponse<IFilesInfos[]>) => this.filesInfos = res.body!,
        () => this.onError()
      ); 
  }

  
  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IChargement): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  /* registerChangeInChargements(): void {
    this.eventSubscriber = this.eventManager.subscribe('chargementListModification', () => this.loadPage());
  } */

  delete(chargement: IChargement): void {
    const modalRef = this.modalService.open(ChargementDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.chargement = chargement;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IChargement[] | null, headers: HttpHeaders, page: number, navigate: boolean): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    if (navigate) {
      this.router.navigate(['/chargement'], {
        queryParams: {
          page: this.page,
          size: this.itemsPerPage,
          sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc'),
        },
      });
    }
    this.chargements = data || [];
    this.ngbPaginationPage = this.page;
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page ?? 1;
  }

  generate(): void {
    alert('date='+this.dateGen+" codeFic="+this.fileInfo?.codeFile+" period="+
    this.periode?.code+" version="+this.version)
    this.chargementService.generate(this.fileInfo?.id!, '31072019' , this.version)
    .subscribe(
      /* (res: HttpResponse<[]>) => /* this.filesInfos = res.body!, 
      () => this.onError() */
    );
  }
}
