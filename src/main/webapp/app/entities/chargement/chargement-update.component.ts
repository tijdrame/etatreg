import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IChargement, Chargement } from 'app/shared/model/chargement.model';
import { ChargementService } from './chargement.service';
import { IFilesInfos } from 'app/shared/model/files-infos.model';
import { FilesInfosService } from 'app/entities/files-infos/files-infos.service';
import { JhiAlertService } from 'ng-jhipster';

@Component({
  selector: 'jhi-chargement-update',
  templateUrl: './chargement-update.component.html',
})
export class ChargementUpdateComponent implements OnInit {
  isSaving = false;
  filesinfos: IFilesInfos[] = [];
  folderFiles: String[] = [];
  isLoading = false;

  editForm = this.fb.group({
    id: [],
    filesInfos: [],
  });

  constructor(
    protected chargementService: ChargementService,
    protected filesInfosService: FilesInfosService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    private jhiAlertService: JhiAlertService
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ chargement }) => {
      this.updateForm(chargement);

      this.filesInfosService.query().subscribe((res: HttpResponse<IFilesInfos[]>) => (this.filesinfos = res.body || []));
      this.filesInfosService.queryTer().subscribe((res: HttpResponse<String[]>) => (this.folderFiles = res.body || []));
    });
  }

  updateForm(chargement: IChargement): void {
    this.editForm.patchValue({
      id: chargement.id,
      filesInfos: chargement.filesInfos,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    /* this.isSaving = true;
    const chargement = this.createFromForm();
    if (chargement.id !== undefined) {
      this.subscribeToSaveResponse(this.chargementService.update(chargement));
    } else {
      this.subscribeToSaveResponse(this.chargementService.create(chargement));
    } */
    console.log('before saving ');
    this.isLoading = true;
    this.chargementService.doChargementBalPaiemt().subscribe((res: any) => {
      console.log('res = ', JSON.stringify(res));
      if (res['status'] === 200) {
        this.jhiAlertService.info('Chargement effectué avec succés');
        this.isLoading = false;
      }
      this.jhiAlertService.warning('Erreur lors du chargement');
      this.isLoading = false;
    });
    console.log('after saving');
  }

  private createFromForm(): IChargement {
    return {
      ...new Chargement(),
      id: this.editForm.get(['id'])!.value,
      filesInfos: this.editForm.get(['filesInfos'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IChargement>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IFilesInfos): any {
    return item.id;
  }
}
