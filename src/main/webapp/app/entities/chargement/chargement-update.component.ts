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

@Component({
  selector: 'jhi-chargement-update',
  templateUrl: './chargement-update.component.html',
})
export class ChargementUpdateComponent implements OnInit {
  isSaving = false;
  filesinfos: IFilesInfos[] = [];

  editForm = this.fb.group({
    id: [],
    filesInfos: [],
  });

  constructor(
    protected chargementService: ChargementService,
    protected filesInfosService: FilesInfosService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ chargement }) => {
      this.updateForm(chargement);

      this.filesInfosService.query().subscribe((res: HttpResponse<IFilesInfos[]>) => (this.filesinfos = res.body || []));
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
    this.isSaving = true;
    const chargement = this.createFromForm();
    if (chargement.id !== undefined) {
      this.subscribeToSaveResponse(this.chargementService.update(chargement));
    } else {
      this.subscribeToSaveResponse(this.chargementService.create(chargement));
    }
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
