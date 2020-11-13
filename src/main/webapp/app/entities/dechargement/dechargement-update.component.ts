import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IDechargement, Dechargement } from 'app/shared/model/dechargement.model';
import { DechargementService } from './dechargement.service';
import { FilesInfosService } from '../files-infos/files-infos.service';

@Component({
  selector: 'jhi-dechargement-update',
  templateUrl: './dechargement-update.component.html',
})
export class DechargementUpdateComponent implements OnInit {
  isSaving = false;
  isLoading = false;
  folderFiles: String[] = [];

  editForm = this.fb.group({
    id: [],
  });

  constructor(
    protected dechargementService: DechargementService,
    protected activatedRoute: ActivatedRoute,
    protected filesInfosService: FilesInfosService,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ dechargement }) => {
      this.updateForm(dechargement);
    });
    this.filesInfosService.queryQua().subscribe((res: HttpResponse<String[]>) => (this.folderFiles = res.body || []));
  }

  updateForm(dechargement: IDechargement): void {
    this.editForm.patchValue({
      id: dechargement.id,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    // this.isSaving = true;
    const dechargement = this.createFromForm();
    if (dechargement.id !== undefined) {
      this.subscribeToSaveResponse(this.dechargementService.update(dechargement));
    } else {
      this.subscribeToSaveResponse(this.dechargementService.create(dechargement));
    }
  }

  private createFromForm(): IDechargement {
    return {
      ...new Dechargement(),
      id: this.editForm.get(['id'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDechargement>>): void {
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
}
